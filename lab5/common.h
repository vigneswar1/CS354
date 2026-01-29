#ifndef COMMON_H
#define COMMON_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>

#define SHM_SIZE sizeof(struct shmseg)

struct shmseg {
    volatile int request_ready;
    volatile int response_ready;
    char request[256];
    char response[275]; // Larger to prevent truncation warning
};

#endif

