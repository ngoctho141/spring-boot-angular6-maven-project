import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class CarService {
    private userUrl = 'https://car-app141.herokuapp.com/api';

    constructor(private http: HttpClient) {
    }

    getAll(): Observable<any> {
        return this.http.get(this.userUrl);
    }

    get(id: string) {
        return this.http.get(this.userUrl + '/' + id);
    }

    save(car: any): Observable<any> {
        let result: Observable<Object>;
        if (car['href']) {
            // debugger
            result = this.http.put(car.href, car);
        } else {
            result = this.http.post(this.userUrl, car);
        }
        return result;
    }

    remove(href: string) {
        //  debugger
        return this.http.delete(href);
    }
}
