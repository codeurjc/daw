import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "src/app/services/login.service";
import { BooksService } from "./../../services/books.service";
import { BookDTO } from "src/app/dtos/book.dto";

@Component({
  templateUrl: "./book-list.component.html",
})
export class BookListComponent implements OnInit {
  public books: BookDTO[];

  constructor(
    private router: Router,
    private booksService: BooksService,
    public loginService: LoginService
  ) {}

  public ngOnInit() {
    this.booksService.getBooks().subscribe(
      (books) => (this.books = books),
      (error) => console.log(error)
    );
  }

  public newBook() {
    this.router.navigate(["/books/new"]);
  }
}
