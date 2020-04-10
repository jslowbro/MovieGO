import { NgModule } from '@angular/core';
import { MovieGoSharedLibsModule } from './shared-libs.module';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';

@NgModule({
  imports: [MovieGoSharedLibsModule],
  declarations: [AlertComponent, AlertErrorComponent, HasAnyAuthorityDirective],
  exports: [MovieGoSharedLibsModule, AlertComponent, AlertErrorComponent, HasAnyAuthorityDirective]
})
export class MovieGoSharedModule {}
