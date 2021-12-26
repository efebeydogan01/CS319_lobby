import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchUserInfoComponent } from './search-user-info.component';

describe('SearchUserInfoComponent', () => {
  let component: SearchUserInfoComponent;
  let fixture: ComponentFixture<SearchUserInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchUserInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
