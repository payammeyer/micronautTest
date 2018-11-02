package com.github.mdavis95.micronaut.test;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public abstract class BaseSecurityFilter implements HttpServerFilter {

	@Override
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {

		return Flowable.fromCallable(() -> {

			//TODO: check session here (code removed for ease of showing example)

			return true;
		}).subscribeOn(Schedulers.io()).switchMap(allowed -> {
			if (allowed) {
				return chain.proceed(request);
			}
			else {
				return Publishers.just(HttpResponse.status(HttpStatus.FORBIDDEN));
			}
		});
	}

}
