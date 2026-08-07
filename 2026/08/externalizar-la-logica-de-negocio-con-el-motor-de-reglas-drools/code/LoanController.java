package io.github.picodotdev.blogbitix.drools.rest;

...

@RestController
@RequestMapping("/loan")
public class LoanController {

    private KieContainer kieContainer;
    private DMNRuntime dmnRuntime;

    public LoanController(KieContainer kieContainer, DMNRuntime dmnRuntime) {
        this.kieContainer = kieContainer;
        this.dmnRuntime = dmnRuntime;
    }

    @PostMapping("/rule")
    public ResponseEntity<LoanResponse> rule(@RequestBody LoanRequest loanRequest) {
        System.out.println("Applicant  id: " + loanRequest.getApplicant().getId());
        System.out.println("Applicant  age: " + loanRequest.getApplicant().getAge());

        List<Command> commands = Arrays.asList(
            CommandFactory.newInsert(loanRequest.getApplicant(), "applicant"),
            CommandFactory.newInsert(loanRequest.getLoanApplication(), "application"),
            new SetActiveAgendaGroup("applicationGroup"),
            CommandFactory.newFireAllRules());

        KieSession kieSession = kieContainer.newKieSession();
        ExecutionResults executionResults = kieSession.execute(CommandFactory.newBatchExecution(commands));
        LoanApplication application = (LoanApplication) executionResults.getResults().get("application");

        System.out.println("Application: " + application);

        return ResponseEntity.ok(new LoanResponse(application));
    }

    @PostMapping("/decision")
    public ResponseEntity<LoanResponse> decision(@RequestBody LoanRequest loanRequest) {
        System.out.println("Applicant  id: " + loanRequest.getApplicant().getId());
        System.out.println("Applicant  age: " + loanRequest.getApplicant().getAge());

        String namespace = "https://kie.org/dmn/_C83DFD16-A42A-46BE-A843-370444580E0F";
        String modelName = "loan-application-age-limit";

        DMNModel dmnModel = dmnRuntime.getModel(namespace, modelName);

        DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("Applicant", loanRequest.getApplicant());
        dmnContext.set("Application", loanRequest.getLoanApplication());
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);

        HashMap<String, Object> result = (HashMap) dmnResult.getDecisionResults().getFirst().getResult();
        LoanApplication application = loanRequest.getLoanApplication();
        application.setApproved((boolean) result.get("approved"));
        application.setExplanation((String) result.get("explanation"));

        return ResponseEntity.ok(new LoanResponse(application));
    }
}
