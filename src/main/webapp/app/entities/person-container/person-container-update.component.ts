import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonContainer, PersonContainer } from 'app/shared/model/person-container.model';
import { PersonContainerService } from './person-container.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person/person.service';

@Component({
  selector: 'jhi-person-container-update',
  templateUrl: './person-container-update.component.html'
})
export class PersonContainerUpdateComponent implements OnInit {
  isSaving = false;
  people: IPerson[] = [];

  editForm = this.fb.group({
    id: [],
    role: [],
    film: [],
    person: []
  });

  constructor(
    protected personContainerService: PersonContainerService,
    protected personService: PersonService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personContainer }) => {
      this.updateForm(personContainer);

      this.personService.query().subscribe((res: HttpResponse<IPerson[]>) => (this.people = res.body || []));
    });
  }

  updateForm(personContainer: IPersonContainer): void {
    this.editForm.patchValue({
      id: personContainer.id,
      role: personContainer.role,
      film: personContainer.film,
      person: personContainer.person
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personContainer = this.createFromForm();
    if (personContainer.id !== undefined) {
      this.subscribeToSaveResponse(this.personContainerService.update(personContainer));
    } else {
      this.subscribeToSaveResponse(this.personContainerService.create(personContainer));
    }
  }

  private createFromForm(): IPersonContainer {
    return {
      ...new PersonContainer(),
      id: this.editForm.get(['id'])!.value,
      role: this.editForm.get(['role'])!.value,
      film: this.editForm.get(['film'])!.value,
      person: this.editForm.get(['person'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonContainer>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IPerson): any {
    return item.id;
  }
}
