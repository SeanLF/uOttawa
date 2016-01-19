class Run{
 
  public static void main(String[] args){
    
    Store store = new Store("myStore");
    Item banana = new Item(true, false, 99);
    Item shampoo = new Item(false, true, 299);
    Item paper = new Item(false, true, 599);
    MembershipCardType specialMiles = new MembershipCardType("specialMiles");
    MembershipCard card = new MembershipCard(12233, specialMiles);
    SpecialPrice specPrice = new SpecialPrice(299, 3, shampoo, specialMiles);
    Purchase purchase = new Purchase("3", shampoo);
    purchase.setMembershipCard(card);
    System.out.println("store "+store+"\n\nbanana "+banana+"\n\nshampoo "+shampoo+"\n\npaper "+paper+"\n\nspecialMiles "+specialMiles+
    "\n\ncard "+card+"\n\nspecPrice "+specPrice+"\n\npurchase "+purchase);
    
  }
  
}