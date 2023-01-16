import java.util.*;

class Job {
    int startTime;
    int endTime;
    int profit;
    Job(int start, int end, int profit) {
        this.startTime = start;
        this.endTime = end;
        this.profit = profit;
    }
}
class JobSelection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of jobs
        System.out.println("Enter the number of Jobs\n");
        int n = sc.nextInt();

        // Create an array of jobs
        Job[] jobs = new Job[n];

        // Input start time, end time, and profit for each job
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(start, end, profit);
        }

        // Sort the jobs based on end time
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                return j1.endTime - j2.endTime;
            }
        });

        // Initialize an array to store maximum profit for each job
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        // Fill dp array in bottom up manner
        for (int i = 1; i < n; i++) {
            // Initialize maximum profit for current job
            int maxProfit = jobs[i].profit;

            // Check for all previous jobs and select the one that is not conflicting
            for (int j = 0; j < i; j++) {
                if (jobs[j].endTime <= jobs[i].startTime) {
                    maxProfit = Math.max(maxProfit, jobs[i].profit + dp[j]);
                }
            }
            dp[i] = maxProfit;
        }

        // Find the maximum profit from dp array
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, dp[i]);
        }

        // Find the number of jobs left and total earnings for other employees
        int task = 0;
        int earnings = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] != maxProfit) {
                task++;
                earnings += jobs[i].profit;
            }
        }

        // Print the number of tasks and earnings available for others
        System.out.println("The number of tasks and earnings available for others");
        System.out.println("Task: " + task);
        sc.nextLine();
        System.out.println("Earnings: " + earnings);
    }
}