#include "common.h"

int main() {
    // 1. Create Key (ensure 'shmfile' exists: touch shmfile)
    key_t key = ftok("shmfile", 65);
    if (key == -1) { perror("ftok"); exit(1); }

    // 2. Get Shared Memory
    int shmid = shmget(key, SHM_SIZE, 0666 | IPC_CREAT);
    if (shmid == -1) { perror("shmget"); exit(1); }

    // 3. Attach
    struct shmseg *shm = (struct shmseg *)shmat(shmid, NULL, 0);
    if (shm == (void *)-1) { perror("shmat"); exit(1); }

    shm->request_ready = 0;
    shm->response_ready = 0;

    printf("Server started. (shmid: %d)\n", shmid);

    while (1) {
        while (shm->request_ready == 0) usleep(1000);

        printf("Server received: %s", shm->request);

        // Safe copy with no truncation
        snprintf(shm->response, sizeof(shm->response), "Processed: %s", shm->request);

        shm->request_ready = 0;
        shm->response_ready = 1;
    }

    shmdt(shm);
    return 0;
}

