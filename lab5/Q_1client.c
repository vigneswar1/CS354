#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MSG_SIZE 256
#define MAX_PRIORITY 10

struct msgbuf {
    long mtype;
    char text[MSG_SIZE];
};

int main() {
    key_t key = ftok("msgqueue", 65);
    int msqid = msgget(key, 0666 | IPC_CREAT);

    struct msgbuf msg;

    while (1) {
        // Receive highest-priority message available
        msgrcv(msqid, &msg, sizeof(msg.text), -MAX_PRIORITY, 0);
        printf("Received (priority %ld): %s\n", msg.mtype, msg.text);
    }

    return 0;
}

