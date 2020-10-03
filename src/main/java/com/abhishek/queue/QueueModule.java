package com.abhishek.queue;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class QueueModule extends AbstractModule {

    int queueSize;

    @Provides
    @QueueSize
    Integer provideQueueSize() {
        return this.queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    @Override
    protected void configure() {
        
    }

    @Qualifier
    @Retention(RUNTIME)
    @interface QueueSize {
    }
}
