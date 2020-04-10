import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRating } from 'app/shared/model/rating.model';

@Component({
  selector: 'jhi-rating-detail',
  templateUrl: './rating-detail.component.html'
})
export class RatingDetailComponent implements OnInit {
  rating: IRating | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rating }) => (this.rating = rating));
  }

  previousState(): void {
    window.history.back();
  }
}
