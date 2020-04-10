import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { MovieGoTestModule } from '../../../test.module';
import { ReviewDetailComponent } from 'app/entities/review/review-detail.component';
import { Review } from 'app/shared/model/review.model';

describe('Component Tests', () => {
  describe('Review Management Detail Component', () => {
    let comp: ReviewDetailComponent;
    let fixture: ComponentFixture<ReviewDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ review: new Review(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MovieGoTestModule],
        declarations: [ReviewDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReviewDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReviewDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load review on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.review).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
