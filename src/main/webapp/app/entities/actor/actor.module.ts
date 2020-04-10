import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MovieGoSharedModule } from 'app/shared/shared.module';
import { ActorComponent } from './actor.component';
import { ActorDetailComponent } from './actor-detail.component';
import { ActorUpdateComponent } from './actor-update.component';
import { ActorDeleteDialogComponent } from './actor-delete-dialog.component';
import { actorRoute } from './actor.route';

@NgModule({
  imports: [MovieGoSharedModule, RouterModule.forChild(actorRoute)],
  declarations: [ActorComponent, ActorDetailComponent, ActorUpdateComponent, ActorDeleteDialogComponent],
  entryComponents: [ActorDeleteDialogComponent]
})
export class MovieGoActorModule {}
