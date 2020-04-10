import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MovieGoSharedModule } from 'app/shared/shared.module';
import { PersonContainerComponent } from './person-container.component';
import { PersonContainerDetailComponent } from './person-container-detail.component';
import { PersonContainerUpdateComponent } from './person-container-update.component';
import { PersonContainerDeleteDialogComponent } from './person-container-delete-dialog.component';
import { personContainerRoute } from './person-container.route';

@NgModule({
  imports: [MovieGoSharedModule, RouterModule.forChild(personContainerRoute)],
  declarations: [
    PersonContainerComponent,
    PersonContainerDetailComponent,
    PersonContainerUpdateComponent,
    PersonContainerDeleteDialogComponent
  ],
  entryComponents: [PersonContainerDeleteDialogComponent]
})
export class MovieGoPersonContainerModule {}
