#include <stdio.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

#define EXEC_DIR_NAME "/exec"
#define DATA_DIR_NAME "/data"
#define WORK_DIR_NAME "/.work"
#define LIVE_DIR_NAME "/live"

#define EXEC_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define DATA_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)
#define WORK_DIR_FLAGS (S_IRWXU)
#define LIVE_DIR_FLAGS (S_IRWXU | S_IRWXG | S_IROTH | S_IXOTH)

int main(int argc, char* argv[]) {

	if (argc != 2) {
		printf("usage: %s <path>\n", argv[0]);
		return -1;
	}

	char* splitpath;
	DIR* splitdir = opendir(splitpath);

	if (splitdir == NULL) {
		perror("opendir");
		return -1;
	}

	char edp[100], ddp[100], wdp[100], ldp[100];
	strcpy(edp, splitpath);
	strcat(edp, EXEC_DIR_NAME);
	strcpy(ddp, splitpath);
	strcat(edp, DATA_DIR_NAME);
	strcpy(wdp, splitpath);
	strcat(wdp, WORK_DIR_NAME);
	strcpy(ldp, splitpath);
	strcat(ldp, LIVE_DIR_NAME);

	if (mkdir(edp, EXEC_DIR_FLAGS) != 0) {
		printf("Could not make exec dir.");
		return -1;
	}

	if (mkdir(ddp, DATA_DIR_FLAGS) != 0) {
		printf("Could not make exec dir.");
		return -1;
	}

	if (mkdir(wdp, WORK_DIR_FLAGS) != 0) {
		printf("Could not make exec dir.");
		return -1;
	}

	if (mkdir(ldp, LIVE_DIR_FLAGS) != 0) {
		printf("Could not make exec dir.");
		return -1;
	}

}
