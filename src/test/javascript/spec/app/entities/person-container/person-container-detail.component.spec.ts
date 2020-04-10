import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MovieGoTestModule } from '../../../test.module';
import { PersonContainerDetailComponent } from 'app/entities/person-container/person-container-detail.component';
import { PersonContainer } from 'app/shared/model/person-container.model';

describe('Component Tests', () => {
  describe('PersonContainer Management Detail Component', () => {
    let comp: PersonContainerDetailComponent;
    let fixture: ComponentFixture<PersonContainerDetailComponent>;
    const route = ({ data: of({ personContainer: new PersonContainer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MovieGoTestModule],
        declarations: [PersonContainerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PersonContainerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersonContainerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load personContainer on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.personContainer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
