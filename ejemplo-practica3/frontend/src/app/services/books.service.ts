import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { BookDTO } from "../dtos/book.dto";

const BASE_URL = "/api/books/";

@Injectable({ providedIn: "root" })
export class BooksService {
  constructor(private httpClient: HttpClient) {}

  public getBooks(): Observable<BookDTO[]> {
    return this.httpClient
      .get(BASE_URL)
      .pipe(catchError((error) => this.handleError(error))) as Observable<
      BookDTO[]
    >;
  }

  public getBook(id: number | string): Observable<BookDTO> {
    return this.httpClient
      .get(BASE_URL + id)
      .pipe(
        catchError((error) => this.handleError(error))
      ) as Observable<BookDTO>;
  }

  public createOrReplaceBook(book: BookDTO): Observable<BookDTO> {
    if (!book.id) {
      return this.httpClient
        .post(BASE_URL, book)
        .pipe(
          catchError((error) => this.handleError(error))
        ) as Observable<BookDTO>;
    } else {
      return this.httpClient
        .put(BASE_URL + book.id, book)
        .pipe(
          catchError((error) => this.handleError(error))
        ) as Observable<BookDTO>;
    }
  }

  public createOrReplaceBookImage(
    book: BookDTO,
    formData: FormData
  ): Observable<BookDTO> {
    if (book.image) {
      return this.httpClient
        .put(BASE_URL + book.id + "/image", formData)
        .pipe(
          catchError((error) => this.handleError(error))
        ) as Observable<BookDTO>;
    } else {
      return this.httpClient
        .post(BASE_URL + book.id + "/image", formData)
        .pipe(
          catchError((error) => this.handleError(error))
        ) as Observable<BookDTO>;
    }
  }

  public deleteBookImage(book: BookDTO): Observable<BookDTO> {
    return this.httpClient
      .delete(BASE_URL + book.id + "/image")
      .pipe(
        catchError((error) => this.handleError(error))
      ) as Observable<BookDTO>;
  }

  public deleteBook(book: BookDTO): Observable<BookDTO> {
    return this.httpClient
      .delete(BASE_URL + book.id)
      .pipe(
        catchError((error) => this.handleError(error))
      ) as Observable<BookDTO>;
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text());
  }
}
