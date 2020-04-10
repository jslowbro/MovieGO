import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MovieGoTestModule } from '../../../test.module';
import { PersonContainerComponent } from 'app/entities/person-container/person-container.component';
import { PersonContainerService } from 'app/entities/person-container/person-container.service';
import { PersonContainer } from 'app/shared/model/person-container.model';

describe('Component Tests', () => {
  describe('PersonContainer Management Component', () => {
    let comp: PersonContainerComponent;
    let fixture: ComponentFixture<PersonContainerComponent>;
    let service: PersonContainerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MovieGoTestModule],
        declarations: [PersonContainerComponent]
      })
        .overrideTemplate(PersonContainerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonContainerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonContainerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PersonContainer(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.personContainers && comp.personContainers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
