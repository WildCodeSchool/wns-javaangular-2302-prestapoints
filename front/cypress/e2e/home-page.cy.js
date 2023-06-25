describe("Prestation home page", () => {
  it("Access to home page and search a prestation wich contains 'dolor' in the card", () => {
    //when
    cy.visit("");
    //given
    cy.get(".search-input").type("dolor");
    cy.get(".discover-button").click();
    //then
    cy.get(".category-text").first().should("have.text", "dolor");
  });

  //Component test
  it("Access to home page and verify than search button contains 'Découvrir'", () => {
    //when
    cy.visit("");
    //then
    cy.get(".discover-button").should("have.text", "Découvrir");
  });
});
