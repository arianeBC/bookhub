import {Component, OnInit} from '@angular/core';
import {BookRequest} from "../../../../services/models/book-request";
import {BookService} from "../../../../services/services/book.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-manage-book',
  templateUrl: './manage-book.component.html',
  styleUrl: './manage-book.component.scss'
})
export class ManageBookComponent implements OnInit {
  bookRequest: BookRequest = {author: "", description: "", isbn: "", title: ""}
  selectedFile: any;
  selectedCoverImage: string | undefined;
  errorMessage: Array<string> = [];
  errorMessagesMap: { [key: string]: string } = {
    '100': 'Title is required.',
    '101': 'Author is required.',
    '102': 'ISBN is required.',
    '103': 'Description is required.'
  };

  constructor(
    private bookService: BookService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const bookId = this.activatedRoute.snapshot.params['bookId'];
    if (bookId) {
      this.bookService.getBook({
        'book-id': bookId
      }).subscribe({
        next: (bookResponse) => {
          this.bookRequest = {
            id: bookResponse.id,
            title: bookResponse.title as string,
            author: bookResponse.author as string,
            isbn: bookResponse.isbn as string,
            description: bookResponse.description as string,
            available: bookResponse.available
          };
          this.selectedCoverImage = 'data:image/jpg;base64,' + bookResponse.coverImage;
        }
      });
    }
  }

  saveBook() {
    this.bookService.savedBook({
      body: this.bookRequest
    }).subscribe({
      next: (bookId) => {
        this.bookService.uploadBookCoverImage({
          'book-id': bookId,
          body: {
            sourceFile: this.selectedFile
          }
        }).subscribe({
          next: () => {
            this.router.navigate(['/books/my-books']);
          }
        });
      },
      error: (err) => {
        console.log(err);
        if (err.error && err.error.validationsErrors) {
          this.errorMessage = err.error.validationsErrors.map((code: string) => this.errorMessagesMap[code] || 'Unknown error occurred');
        } else {
          this.errorMessage = ['An unexpected error occurred. Please try again.'];
        }
      }
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile);
    if (this.selectedFile) {
      const reader = new FileReader;
      reader.onload = () => {
        this.selectedCoverImage = reader.result as string;
      }
      reader.readAsDataURL(this.selectedFile);
    }
  }
}
