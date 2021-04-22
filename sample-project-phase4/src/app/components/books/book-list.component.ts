import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { Book } from './../../models/book.model';
import { BooksService } from './../../services/books.service';


@Component({
    template: `
    <h2>Books</h2>
    <ul class="items">
      <li *ngFor="let book of books">
        <a [routerLink]="['/books', book.id]">{{book.title}}</a>
      </li>
    </ul>
    <button *ngIf="loginService.isLogged()" (click)="newBook()">New book</button>
  `
})
export class BookListComponent implements OnInit {

    books: Book[];

    constructor(private router:Router, private service: BooksService, public loginService: LoginService) {}

    ngOnInit(){
      this.service.getBooks().subscribe(
        books => this.books = books,
        error => console.log(error)
      );
    }

    newBook() {
      this.router.navigate(['/books/new']);
    }
}
