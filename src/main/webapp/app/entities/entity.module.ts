import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.MovieGoRegionModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.MovieGoCountryModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.MovieGoLocationModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.MovieGoDepartmentModule)
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.MovieGoTaskModule)
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.MovieGoEmployeeModule)
      },
      {
        path: 'job',
        loadChildren: () => import('./job/job.module').then(m => m.MovieGoJobModule)
      },
      {
        path: 'job-history',
        loadChildren: () => import('./job-history/job-history.module').then(m => m.MovieGoJobHistoryModule)
      },
      {
        path: 'person',
        loadChildren: () => import('./person/person.module').then(m => m.MovieGoPersonModule)
      },
      {
        path: 'comment',
        loadChildren: () => import('./comment/comment.module').then(m => m.MovieGoCommentModule)
      },
      {
        path: 'review',
        loadChildren: () => import('./review/review.module').then(m => m.MovieGoReviewModule)
      },
      {
        path: 'rating',
        loadChildren: () => import('./rating/rating.module').then(m => m.MovieGoRatingModule)
      },
      {
        path: 'person-container',
        loadChildren: () => import('./person-container/person-container.module').then(m => m.MovieGoPersonContainerModule)
      },
      {
        path: 'film',
        loadChildren: () => import('./film/film.module').then(m => m.MovieGoFilmModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class MovieGoEntityModule {}
