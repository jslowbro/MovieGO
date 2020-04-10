import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MovieGoSharedModule } from 'app/shared/shared.module';
import { MovieGoCoreModule } from 'app/core/core.module';
import { MovieGoAppRoutingModule } from './app-routing.module';
import { MovieGoHomeModule } from './home/home.module';
import { MovieGoEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MovieGoSharedModule,
    MovieGoCoreModule,
    MovieGoHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MovieGoEntityModule,
    MovieGoAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class MovieGoAppModule {}
