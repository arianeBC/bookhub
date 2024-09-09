import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import {BookRoutingModule} from './book-routing.module';
import {MainComponent} from './pages/main/main.component';
import {MenuComponent} from './components/menu/menu.component';
import {BookListComponent} from './pages/book-list/book-list.component';
import {BookCardComponent} from './components/book-card/book-card.component';
import { RatingComponent } from './components/rating/rating.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    BookListComponent,
    BookCardComponent,
    RatingComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    NgOptimizedImage
  ]
})
export class BookModule {
}
