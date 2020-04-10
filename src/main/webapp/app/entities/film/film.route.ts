import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFilm, Film } from 'app/shared/model/film.model';
import { FilmService } from './film.service';
import { FilmComponent } from './film.component';
import { FilmDetailComponent } from './film-detail.component';
import { FilmUpdateComponent } from './film-update.component';

@Injectable({ providedIn: 'root' })
export class FilmResolve implements Resolve<IFilm> {
  constructor(private service: FilmService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFilm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((film: HttpResponse<Film>) => {
          if (film.body) {
            return of(film.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Film());
  }
}

export const filmRoute: Routes = [
  {
    path: '',
    component: FilmComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Films'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FilmDetailComponent,
    resolve: {
      film: FilmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Films'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FilmUpdateComponent,
    resolve: {
      film: FilmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Films'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FilmUpdateComponent,
    resolve: {
      film: FilmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Films'
    },
    canActivate: [UserRouteAccessService]
  }
];
