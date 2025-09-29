@Csrf	 
void onSuccessFromCsrfForm() {
	cuenta += 1;
	renderer.addRender("zone", zone).addRender("submitOneZone", submitOneZone).addRender("csrfZone", csrfZone);
}
	 
@Csrf
void onSumar1CuentaCsrf() {
	cuenta += 1;
	renderer.addRender("zone", zone).addRender("submitOneZone", submitOneZone).addRender("csrfZone", csrfZone);
}