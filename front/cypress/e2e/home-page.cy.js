describe("Prestation home page", () => {
  
  it("Access to home page and search a prestation wich contains dolor in the card", () => {
    cy.visit("");

    cy.get(".search-input").type("dolor");
    cy.get(".discover-button").click();
    cy.get(".category-text").first().should("have.text", "dolor");
  });


  it("Access to home page and verify than search button contains 'Découvrir'", () => {
    cy.visit("");

    cy.get(".discover-button").should("have.text", "Découvrir");
  });


});
