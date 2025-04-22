import { Component, ElementRef, TemplateRef, ViewChild } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

import { BooksService } from "./../../services/books.service";
import { BookDTO } from "src/app/dtos/book.dto";
import { ShopBasicDTO } from "src/app/dtos/shopbasic.dto";
import { ShopsService } from "src/app/services/shops.service";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  templateUrl: "./book-form.component.html",
})
export class BookFormComponent {
  public newBook: boolean;
  public book: BookDTO;
  public oldTitle: string;
  public shops: ShopBasicDTO[];
  public selectedBooks: number[] = [];

  @ViewChild("file")
  public file: ElementRef;
  @ViewChild("messageErrorModal")
  public messageErrorModal: TemplateRef<void>;

  public removeImage: boolean;
  public messageError: string;

  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private booksService: BooksService,
    private shopsService: ShopsService,
    private modalService: NgbModal
  ) {
    const id = activatedRoute.snapshot.params["id"];

    if (id) {
      this.booksService.getBook(id).subscribe(
        (book) => {
          this.book = book;
          this.oldTitle = this.book.title;
          this.selectedBooks = this.book.shops.map((shop) => shop.id);
        },
        (error) => console.error(error)
      );

      this.newBook = false;
    } else {
      this.book = { title: "", description: "", image: false, shops: [] };
      this.newBook = true;
    }

    this.shopsService.getShops().subscribe(
      (shops) => (this.shops = shops),
      (error) => console.error(error)
    );
  }

  public cancel() {
    if (this.newBook) {
      this.router.navigate(["/books"]);
    } else {
      this.router.navigate(["/books", this.book.id]);
    }
  }

  save() {
    this.book.shops = this.shops.filter((shop) =>
      this.selectedBooks.includes(shop.id)
    );

    this.booksService.createOrReplaceBook(this.book).subscribe(
      (book: BookDTO) => this.uploadImage(book),
      (error) => {
        this.messageError = "Error creating new book: " + error;
        this.modalService.open(this.messageErrorModal, { centered: true });
      }
    );
  }

  uploadImage(book: BookDTO): void {
    const image = this.file.nativeElement.files[0];

    console.log(this.removeImage);

    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);

      this.booksService.createOrReplaceBookImage(book, formData).subscribe(
        (_) => this.afterUploadImage(book),
        (error) => {
          this.messageError = "Error uploading book image: " + error;
          this.modalService.open(this.messageErrorModal, { centered: true });
        }
      );
    } else if (this.removeImage) {
      this.booksService.deleteBookImage(book).subscribe(
        (_) => this.afterUploadImage(book),
        (error) => {
          this.messageError = "Error uploading book image: " + error;
          this.modalService.open(this.messageErrorModal, { centered: true });
        }
      );
    } else {
      this.afterUploadImage(book);
    }
  }

  private afterUploadImage(book: BookDTO) {
    this.router.navigate(["/books/", book.id]);
  }

  bookImage() {
    return this.book.image
      ? "/api/books/" + this.book.id + "/image"
      : "/assets/images/no_image.png";
  }
}
