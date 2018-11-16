/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HoSoThanNhanService } from 'app/entities/ho-so-than-nhan/ho-so-than-nhan.service';
import { IHoSoThanNhan, HoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';

describe('Service Tests', () => {
    describe('HoSoThanNhan Service', () => {
        let injector: TestBed;
        let service: HoSoThanNhanService;
        let httpMock: HttpTestingController;
        let elemDefault: IHoSoThanNhan;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(HoSoThanNhanService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new HoSoThanNhan(
                0,
                'AAAAAAA',
                false,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        namSinh: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a HoSoThanNhan', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        namSinh: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        namSinh: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new HoSoThanNhan(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a HoSoThanNhan', async () => {
                const returnedFromService = Object.assign(
                    {
                        maThanNhan: 'BBBBBB',
                        isLayMau: true,
                        hoTen: 'BBBBBB',
                        namSinh: currentDate.format(DATE_TIME_FORMAT),
                        gioiTinh: 'BBBBBB',
                        soCMT: 'BBBBBB',
                        diaChi: 'BBBBBB',
                        dienThoaiChinh: 'BBBBBB',
                        dienThoaiPhu: 'BBBBBB',
                        email: 'BBBBBB',
                        ghiChu: 'BBBBBB',
                        isDeleted: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        namSinh: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of HoSoThanNhan', async () => {
                const returnedFromService = Object.assign(
                    {
                        maThanNhan: 'BBBBBB',
                        isLayMau: true,
                        hoTen: 'BBBBBB',
                        namSinh: currentDate.format(DATE_TIME_FORMAT),
                        gioiTinh: 'BBBBBB',
                        soCMT: 'BBBBBB',
                        diaChi: 'BBBBBB',
                        dienThoaiChinh: 'BBBBBB',
                        dienThoaiPhu: 'BBBBBB',
                        email: 'BBBBBB',
                        ghiChu: 'BBBBBB',
                        isDeleted: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        namSinh: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a HoSoThanNhan', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
