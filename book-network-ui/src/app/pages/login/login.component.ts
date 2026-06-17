import { Component } from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {Router} from "@angular/router";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']  // التغيير هنا
})

export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {
  }

   login() {
    this.errorMsg = [];
    // يقوم هذا الجزء بإرسال طلب مصادقة إلى الخادم باستخدام خدمة authService
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      // إذا كان الطلب ناجحًا (خادم المصادقة يعيد استجابة صحيحة)، يتم تنفيذ جزء next
      next: (res) => {
        this.tokenService.token = res.token as string;
        this.router.navigate(['books']);
      },
      // إذا حدث خطأ أثناء طلب المصادقة، يتم تنفيذ جزء error
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
      }
    });
  }
  //
  register() {
    //  سيقوم التطبيق بتغيير الصفحة أو العرض الحالي إلى صفحة التسجيل
     this.router.navigate(['register']);
   }

}
