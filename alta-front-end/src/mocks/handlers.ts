import { http, HttpResponse } from 'msw';

import { config } from '../core/config';
import { MOCK_STUDENTS } from './data/students.mock';

export const handlers = [
	http.get(`${config.apiHost}/api/v1/students`, () => {
		return HttpResponse.json(MOCK_STUDENTS);
	}),
];
