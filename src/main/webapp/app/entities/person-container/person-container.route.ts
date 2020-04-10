import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonContainer, PersonContainer } from 'app/shared/model/person-container.model';
import { PersonContainerService } from './person-container.service';
import { PersonContainerComponent } from './person-container.component';
import { PersonContainerDetailComponent } from './person-container-detail.component';
import { PersonContainerUpdateComponent } from './person-container-update.component';

@Injectable({ providedIn: 'root' })
export class PersonContainerResolve implements Resolve<IPersonContainer> {
  constructor(private service: PersonContainerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonContainer> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personContainer: HttpResponse<PersonContainer>) => {
          if (personContainer.body) {
            return of(personContainer.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonContainer());
  }
}

export const personContainerRoute: Routes = [
  {
    path: '',
    component: PersonContainerComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonContainers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PersonContainerDetailComponent,
    resolve: {
      personContainer: PersonContainerResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonContainers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PersonContainerUpdateComponent,
    resolve: {
      personContainer: PersonContainerResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonContainers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PersonContainerUpdateComponent,
    resolve: {
      personContainer: PersonContainerResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonContainers'
    },
    canActivate: [UserRouteAccessService]
  }
];
