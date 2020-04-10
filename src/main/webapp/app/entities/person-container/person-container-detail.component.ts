import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonContainer } from 'app/shared/model/person-container.model';

@Component({
  selector: 'jhi-person-container-detail',
  templateUrl: './person-container-detail.component.html'
})
export class PersonContainerDetailComponent implements OnInit {
  personContainer: IPersonContainer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personContainer }) => (this.personContainer = personContainer));
  }

  previousState(): void {
    window.history.back();
  }
}
