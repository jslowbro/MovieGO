import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MovieGoSharedModule } from 'app/shared/shared.module';
import { DirectorComponent } from './director.component';
import { DirectorDetailComponent } from './director-detail.component';
import { DirectorUpdateComponent } from './director-update.component';
import { DirectorDeleteDialogComponent } from './director-delete-dialog.component';
import { directorRoute } from './director.route';

@NgModule({
  imports: [MovieGoSharedModule, RouterModule.forChild(directorRoute)],
  declarations: [DirectorComponent, DirectorDetailComponent, DirectorUpdateComponent, DirectorDeleteDialogComponent],
  entryComponents: [DirectorDeleteDialogComponent]
})
export class MovieGoDirectorModule {}
