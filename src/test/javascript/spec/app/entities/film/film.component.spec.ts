import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MovieGoTestModule } from '../../../test.module';
import { FilmComponent } from 'app/entities/film/film.component';
import { FilmService } from 'app/entities/film/film.service';
import { Film } from 'app/shared/model/film.model';

describe('Component Tests', () => {
  describe('Film Management Component', () => {
    let comp: FilmComponent;
    let fixture: ComponentFixture<FilmComponent>;
    let service: FilmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MovieGoTestModule],
        declarations: [FilmComponent]
      })
        .overrideTemplate(FilmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FilmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FilmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Film(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.films && comp.films[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
