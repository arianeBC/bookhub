import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrl: './rating.component.scss'
})
export class RatingComponent {

  @Input() rating: number = 0;
  @Input() ratingEnabled: boolean = true;
  @Output() ratingChange = new EventEmitter<number>();
  maxRating: number = 5;
  hoveredRating: number = 0;

  get fullStars() {
    return Math.floor(this.rating);
  }

  get hasHalfStar(): boolean {
    return this.rating % 1 !== 0;
  }

  get emptyStars(): number {
    return this.maxRating - this.fullStars - (this.hasHalfStar ? 1 : 0);
  }

  onStarClick(starValue: number) {
    if (this.ratingEnabled) {
      this.rating = starValue;
      this.ratingChange.emit(starValue);
    }
  }

  onStarHover(starValue: number) {
    if (this.ratingEnabled) {
      this.hoveredRating = starValue;
    }
  }

  onStarLeave() {
    if (this.ratingEnabled) {
      this.hoveredRating = 0;
    }
  }

  isStarHighlighted(index: number): boolean {
    return index + -1 < this.hoveredRating || index < this.fullStars;
  }
}
