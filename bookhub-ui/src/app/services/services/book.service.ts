/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approveBookReturn } from '../fn/book/approve-book-return';
import { ApproveBookReturn$Params } from '../fn/book/approve-book-return';
import { BookResponse } from '../models/book-response';
import { borrowBook } from '../fn/book/borrow-book';
import { BorrowBook$Params } from '../fn/book/borrow-book';
import { getAllBooks } from '../fn/book/get-all-books';
import { GetAllBooks$Params } from '../fn/book/get-all-books';
import { getAllBooksByOwner } from '../fn/book/get-all-books-by-owner';
import { GetAllBooksByOwner$Params } from '../fn/book/get-all-books-by-owner';
import { getAllBorrowedBooks } from '../fn/book/get-all-borrowed-books';
import { GetAllBorrowedBooks$Params } from '../fn/book/get-all-borrowed-books';
import { getAllReturnedBooks } from '../fn/book/get-all-returned-books';
import { GetAllReturnedBooks$Params } from '../fn/book/get-all-returned-books';
import { getBook } from '../fn/book/get-book';
import { GetBook$Params } from '../fn/book/get-book';
import { PageResponseBookResponse } from '../models/page-response-book-response';
import { PageResponseBorrowedBookResponse } from '../models/page-response-borrowed-book-response';
import { returnBorrowedBook } from '../fn/book/return-borrowed-book';
import { ReturnBorrowedBook$Params } from '../fn/book/return-borrowed-book';
import { savedBook } from '../fn/book/saved-book';
import { SavedBook$Params } from '../fn/book/saved-book';
import { updateArchivedStatus } from '../fn/book/update-archived-status';
import { UpdateArchivedStatus$Params } from '../fn/book/update-archived-status';
import { updateAvailableStatus } from '../fn/book/update-available-status';
import { UpdateAvailableStatus$Params } from '../fn/book/update-available-status';
import { uploadBookCoverImage } from '../fn/book/upload-book-cover-image';
import { UploadBookCoverImage$Params } from '../fn/book/upload-book-cover-image';

@Injectable({ providedIn: 'root' })
export class BookService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAllBooks()` */
  static readonly GetAllBooksPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooks$Response(params?: GetAllBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return getAllBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooks(params?: GetAllBooks$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.getAllBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

  /** Path part for operation `savedBook()` */
  static readonly SavedBookPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `savedBook()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savedBook$Response(params: SavedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return savedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `savedBook$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savedBook(params: SavedBook$Params, context?: HttpContext): Observable<number> {
    return this.savedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `uploadBookCoverImage()` */
  static readonly UploadBookCoverImagePath = '/books/cover/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadBookCoverImage()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCoverImage$Response(params: UploadBookCoverImage$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadBookCoverImage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadBookCoverImage$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCoverImage(params: UploadBookCoverImage$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadBookCoverImage$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowBook()` */
  static readonly BorrowBookPath = '/books/borrow/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook$Response(params: BorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook(params: BorrowBook$Params, context?: HttpContext): Observable<number> {
    return this.borrowBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnBorrowedBook()` */
  static readonly ReturnBorrowedBookPath = '/books/borrow/return/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBorrowedBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook$Response(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnBorrowedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBorrowedBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<number> {
    return this.returnBorrowedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approveBookReturn()` */
  static readonly ApproveBookReturnPath = '/books/borrow/return/approve/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveBookReturn()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveBookReturn$Response(params: ApproveBookReturn$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approveBookReturn(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveBookReturn$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveBookReturn(params: ApproveBookReturn$Params, context?: HttpContext): Observable<number> {
    return this.approveBookReturn$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateAvailableStatus()` */
  static readonly UpdateAvailableStatusPath = '/books/available/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateAvailableStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateAvailableStatus$Response(params: UpdateAvailableStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateAvailableStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateAvailableStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateAvailableStatus(params: UpdateAvailableStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateAvailableStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateArchivedStatus()` */
  static readonly UpdateArchivedStatusPath = '/books/archived/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateArchivedStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchivedStatus$Response(params: UpdateArchivedStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateArchivedStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateArchivedStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchivedStatus(params: UpdateArchivedStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateArchivedStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getBook()` */
  static readonly GetBookPath = '/books/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBook$Response(params: GetBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return getBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBook(params: GetBook$Params, context?: HttpContext): Observable<BookResponse> {
    return this.getBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `getAllReturnedBooks()` */
  static readonly GetAllReturnedBooksPath = '/books/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllReturnedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllReturnedBooks$Response(params?: GetAllReturnedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBookResponse>> {
    return getAllReturnedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllReturnedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllReturnedBooks(params?: GetAllReturnedBooks$Params, context?: HttpContext): Observable<PageResponseBorrowedBookResponse> {
    return this.getAllReturnedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBookResponse>): PageResponseBorrowedBookResponse => r.body)
    );
  }

  /** Path part for operation `getAllBooksByOwner()` */
  static readonly GetAllBooksByOwnerPath = '/books/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooksByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksByOwner$Response(params?: GetAllBooksByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return getAllBooksByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooksByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksByOwner(params?: GetAllBooksByOwner$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.getAllBooksByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

  /** Path part for operation `getAllBorrowedBooks()` */
  static readonly GetAllBorrowedBooksPath = '/books/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBorrowedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBorrowedBooks$Response(params?: GetAllBorrowedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedBookResponse>> {
    return getAllBorrowedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBorrowedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBorrowedBooks(params?: GetAllBorrowedBooks$Params, context?: HttpContext): Observable<PageResponseBorrowedBookResponse> {
    return this.getAllBorrowedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedBookResponse>): PageResponseBorrowedBookResponse => r.body)
    );
  }

}
