import { Component, TemplateRef, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { BooksService } from "../../services/books.service";
import { LoginService } from "src/app/services/login.service";
import { BookDTO } from "src/app/dtos/book.dto";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  templateUrl: "./book-detail.component.html",
})
export class BookDetailComponent {
  @ViewChild("bookRemoveModal")
  public bookRemoveModal: TemplateRef<void>;

  public book: BookDTO;

  constructor(
    private router: Router,
    public activatedRoute: ActivatedRoute,
    public booksService: BooksService,
    public loginService: LoginService,
    private modalService: NgbModal
  ) {
    const id = activatedRoute.snapshot.params["id"];

    this.booksService.getBook(id).subscribe(
      (book) => (this.book = book),
      (error) => console.error(error)
    );
  }

  public removeBook() {
    const modalRef = this.modalService.open(this.bookRemoveModal, {
      centered: true,
    });

    modalRef.result.then((result) => {
      if (result === "remove") {
        this.booksService.deleteBook(this.book).subscribe(
          (_) => this.router.navigate(["/books"]),
          (error) => console.error(error)
        );
      }
    });
  }

  public bookImage() {
    return this.book.image
      ? "/api/books/" + this.book.id + "/image"
      : "/assets/images/no_image.png";
  }
}
