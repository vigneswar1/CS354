#include "common.h"

int main() {
    key_t key = ftok("shmfile", 65);
    if (key == -1) { perror("ftok"); exit(1); }

    int shmid = shmget(key, SHM_SIZE, 0666 | IPC_CREAT);
    if (shmid == -1) { perror("shmget"); exit(1); }

    struct shmseg *shm = (struct shmseg *)shmat(shmid, NULL, 0);
    if (shm == (void *)-1) { perror("shmat"); exit(1); }

    char input[256]; // FIXED: Must be an array, not a char

    while (1) {
        printf("Enter request (or 'exit'): ");
        if (!fgets(input, sizeof(input), stdin)) break;

        if (strncmp(input, "exit", 4) == 0) break;

        // Copy to shared memory
        strncpy(shm->request, input, sizeof(shm->request) - 1);
        shm->request_ready = 1;

        while (shm->response_ready == 0) usleep(1000);

        printf("Client received: %s", shm->response);
        shm->response_ready = 0;
    }

    shmdt(shm);
    return 0;
}

