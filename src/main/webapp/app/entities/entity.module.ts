import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'person',
        loadChildren: () => import('./person/person.module').then(m => m.MovieGoPersonModule)
      },
      {
        path: 'actor',
        loadChildren: () => import('./actor/actor.module').then(m => m.MovieGoActorModule)
      },
      {
        path: 'director',
        loadChildren: () => import('./director/director.module').then(m => m.MovieGoDirectorModule)
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
        path: 'film',
        loadChildren: () => import('./film/film.module').then(m => m.MovieGoFilmModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class MovieGoEntityModule {}
