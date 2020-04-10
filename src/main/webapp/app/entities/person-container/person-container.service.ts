import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersonContainer } from 'app/shared/model/person-container.model';

type EntityResponseType = HttpResponse<IPersonContainer>;
type EntityArrayResponseType = HttpResponse<IPersonContainer[]>;

@Injectable({ providedIn: 'root' })
export class PersonContainerService {
  public resourceUrl = SERVER_API_URL + 'api/person-containers';

  constructor(protected http: HttpClient) {}

  create(personContainer: IPersonContainer): Observable<EntityResponseType> {
    return this.http.post<IPersonContainer>(this.resourceUrl, personContainer, { observe: 'response' });
  }

  update(personContainer: IPersonContainer): Observable<EntityResponseType> {
    return this.http.put<IPersonContainer>(this.resourceUrl, personContainer, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonContainer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonContainer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
