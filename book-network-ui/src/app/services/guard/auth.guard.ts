import {CanActivateFn, Router} from '@angular/router';
import {inject, PLATFORM_ID} from '@angular/core';
import {isPlatformBrowser} from '@angular/common';
import {KeycloakService} from '../keycloak/keycloak.service';

export const authGuard: CanActivateFn = () => {
  const platformId = inject(PLATFORM_ID);

  // وقت SSR مفيش document/window فمش هينفذ الـ check ده
  // خليه يعدي عادي وسيب الـ check الحقيقي للمتصفح بعد ما يحصل hydration
  if (!isPlatformBrowser(platformId)) {
    return true;
  }

  const keycloakService = inject(KeycloakService);
  const router = inject(Router);

  if (!keycloakService.keycloak || keycloakService.keycloak.isTokenExpired()) {
    router.navigate(['login']);
    return false;
  }
  return true;
};
