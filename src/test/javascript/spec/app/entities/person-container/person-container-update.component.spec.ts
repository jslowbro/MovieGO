import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MovieGoTestModule } from '../../../test.module';
import { PersonContainerUpdateComponent } from 'app/entities/person-container/person-container-update.component';
import { PersonContainerService } from 'app/entities/person-container/person-container.service';
import { PersonContainer } from 'app/shared/model/person-container.model';

describe('Component Tests', () => {
  describe('PersonContainer Management Update Component', () => {
    let comp: PersonContainerUpdateComponent;
    let fixture: ComponentFixture<PersonContainerUpdateComponent>;
    let service: PersonContainerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MovieGoTestModule],
        declarations: [PersonContainerUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PersonContainerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonContainerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonContainerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersonContainer(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersonContainer();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
