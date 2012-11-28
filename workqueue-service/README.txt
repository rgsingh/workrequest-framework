Explanation of Usage:

Launching the "daemon" or backend thread is achieved by running com.rgsinfotech.workqueue.Server 
in the workqueue-service module. The workqueue-frontend-remote module's 
com.rgsinfotech.workqueue.client.remote.Client class is used to communicate with the daemon process
remotely. 