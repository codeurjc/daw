import { Component, TemplateRef, ViewChild } from "@angular/core";
import { LoginService } from "../../services/login.service";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "login",
  templateUrl: "./login.component.html",
})
export class LoginComponent {
  @ViewChild("loginErrorModal")
  public loginErrorModal: TemplateRef<void>;

  constructor(
    public loginService: LoginService,
    private modalService: NgbModal
  ) {}

  public logIn(user: string, pass: string) {
    this.loginService.logIn(user, pass).subscribe(
      (_) => {
        this.loginService.reqIsLogged();
      },
      (_) => {
        this.modalService.open(this.loginErrorModal, { centered: true });
      }
    );
  }

  public logOut() {
    this.loginService.logOut();
  }
}
