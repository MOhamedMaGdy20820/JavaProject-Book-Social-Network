import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenService} from '../token/token.service';

@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService
  ) {}

  /*

  بضيف ال token في طلبات ال http


  intercept: هذه هي الدالة التي يتم استدعاؤها عند إجراء أي طلب HTTP. تتلقى دالة intercept معطيات هي:
  request: كائن يمثل طلب HTTP الأصلي.
  next: كائن من نوع HttpHandler يستخدم لإرسال الطلب بعد تعديله (إذا تم تعديله).
   */


  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = this.tokenService.token;

    if (token) {
      const authReq = request.clone({
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + token
        })
      });
      return next.handle(authReq);
    }
    return next.handle(request);
  }
}
