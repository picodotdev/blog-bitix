package io.github.picodotdev.blogbitix.temporal;

...

@SpringBootApplication
public class Main implements ApplicationRunner {

    public static final String PLACE_ORDER_WORKFLOW_TASK_QUEUE = "place-order-workflow-task-queue";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PlaceOrderWorkflowWorker worker = new PlaceOrderWorkflowWorker();
        Workflow workflow = new Workflow();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> worker.start());
        Thread.sleep(1000);
        executor.submit(() -> workflow.run());
        Thread.sleep(5000);

        worker.shutdown();
        workflow.shutdown();
        executor.shutdown();
    }
}
