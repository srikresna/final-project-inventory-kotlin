import java.util.Scanner

object Main {
    //global variable
    private var sc = Scanner(System.`in`)
    private var menu = 0
    private var itemIndex = 0
    private var itemCode: String? = null
    private var yesNo: String? = null
    private var mainProcess = false
    private var menuProcess = false
    private var inventoryProcess = false
    private var borrowingProcess = false
    private var validationUser = false
    private var userMain = arrayOf("admin", "admin")
    private var getUser = arrayOfNulls<String>(2)
    private var inventory = arrayOf(
        arrayOf("A1", "monitor", "20", "3"),
        arrayOf("A2", "keyboard", "78", "11"),
        arrayOf("A3", "mouse", "87", "7"),
        arrayOf("A4", "table", "130", "10"),
        arrayOf("A5", "chair", "20", "4"),
        arrayOf("A6", "projector", "9", "0"),
        arrayOf("A7", "switch", "24", "1"),
        arrayOf("A8", "laptop", "2", "5")
    )
    private var borrowItem = arrayOf(
        arrayOf("7-12-2022", "A8", "laptop", "1"),
        arrayOf("7-12-2022", "A7", "switch", "2"),
        arrayOf("7-12-2022", "A6", "projector", "1")
    )
    private var frameInventory = Array(50) {
        arrayOfNulls<String>(
            4
        )
    }
    private var frameBorrowing = Array(50) {
        arrayOfNulls<String>(
            4
        )
    }

    //validation user function
    private fun validationUser() {
        println("Enter username: ")
        getUser[0] = sc.nextLine()
        println("Enter password: ")
        getUser[1] = sc.nextLine()
        if (getUser[0] == userMain[0] && getUser[1] == userMain[1]) {
            validationUser = true
        } else {
            println("Username or password is wrong")
            validationUser = false
        }
    }


    //array-maker function
    private fun setFrameItem() {
        for (i in inventory.indices) {
            frameInventory[i][0] = inventory[i][0]
            frameInventory[i][1] = inventory[i][1]
            frameInventory[i][2] = inventory[i][2]
            frameInventory[i][3] = inventory[i][3]
        }
        var i = 0
        do {
            frameBorrowing[i][0] = borrowItem[i][0]
            frameBorrowing[i][1] = borrowItem[i][1]
            frameBorrowing[i][2] = borrowItem[i][2]
            frameBorrowing[i][3] = borrowItem[i][3]
            i++
        } while (i < borrowItem.size)
    }

    //login function
    private fun loginPage() {
        println("Welcome to Inventory System")
        println("Please login to continue")
        validationUser()
        if (validationUser) {
            mainProcess = true
        }
    }

    //print inventory function
    private fun showInventory() {
        println("\n================== INVENTORY ==================")
        println("| No | Code | Item Name      | Stock | Broken |")
        for (i in frameInventory.indices) {
            if (frameInventory[i][0] != null) {
                System.out.printf(
                    "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", i + 1,
                    frameInventory[i][0],
                    frameInventory[i][1], frameInventory[i][2], frameInventory[i][3]
                )
            }
        }
    }

    //input new item function
    private fun inputNewItem() {
        for (i in frameInventory.indices) {
            if (frameInventory[i][0] == null) {
                print("Input item code : ")
                frameInventory[i][0] = sc.nextLine()
                print("Input item name : ")
                frameInventory[i][1] = sc.nextLine()
                print("Input new stock : ")
                frameInventory[i][2] = sc.nextLine()
                print("Input total broken : ")
                frameInventory[i][3] = sc.nextLine()
                print("Do you want to input again? ( y / n ) : ")
                yesNo = sc.nextLine()
                if (yesNo.equals("n", ignoreCase = true)) {
                    break
                }
            }
        }
    }

    //searching array inventory itemCode
    private fun findItemCode(frameInventory: Array<Array<String?>>, code: String?): Int {
        for (i in frameInventory.indices) {
            if (frameInventory[i][0] != null) {
                if (frameInventory[i][0].equals(code, ignoreCase = true)) {
                    return i
                }
            }
        }
        return -1
    }

    //searching array inventory itemName
    private fun findItemName(frameInventory: Array<Array<String?>>, name: String?): Int {
        for (i in frameInventory.indices) {
            if (frameInventory[i][1] != null) {
                if (frameInventory[i][1].equals(name, ignoreCase = true)) {
                    return i
                }
            }
        }
        return -1
    }

    //searching array borrowing
    private fun findBorrowing(frameBorrowing: Array<Array<String?>>, itemCode: String?): Int {
        for (i in frameBorrowing.indices) {
            if (frameBorrowing[i][0] != null) {
                if (frameBorrowing[i][1].equals(itemCode, ignoreCase = true)) {
                    return i
                }
            }
        }
        return -1
    }

    // menu edit item function
    private fun editItem() {
        var menuEdit = true
        var subMenuEdit = true
        itemIndex = 0
        while (menuEdit) {
            print("Input item code to edit : ")
            itemCode = sc.nextLine()
            itemIndex = findItemCode(frameInventory, itemCode)
            if (itemIndex == -1) {
                println("item not found!")
            } else {
                while (subMenuEdit) {
                    println("\nItem found! Here's item that you want edit : ")
                    println("| No | Code | Item Name      | Stock | Broken |")
                    System.out.printf(
                        "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", itemIndex + 1,
                        frameInventory[itemIndex][0],
                        frameInventory[itemIndex][1],
                        frameInventory[itemIndex][2], frameInventory[itemIndex][3]
                    )
                    println("\nWhat do you want to edit? \n1. Item code \n2. Item name \n3. Item stock \n4. Item broken \n5. Nothing")
                    print("Select option 1 - 5 : ")
                    menu = sc.nextInt()
                    sc.nextLine()
                    when (menu) {
                        1 -> {
                            print("Input new item code : ")
                            frameInventory[itemIndex][0] = sc.nextLine()
                        }
                        2 -> {
                            print("Input new item name : ")
                            frameInventory[itemIndex][1] = sc.nextLine()
                        }
                        3 -> {
                            print("Input new item stock : ")
                            frameInventory[itemIndex][2] = sc.nextLine()
                        }
                        4 -> {
                            print("Input total broken condition : ")
                            frameInventory[itemIndex][3] = sc.nextLine()
                        }
                        5 -> subMenuEdit = false
                        else -> println("\t\tInvalid option! select option correctly")
                    }
                }
                print("Do you want to edit again? ( y / n ) : ")
                yesNo = sc.nextLine()
                if (yesNo.equals("n", ignoreCase = true)) {
                    menuEdit = false
                }
            }
        }
    }

    //find item function
    private fun findItem() {
        itemIndex = 0
        var subMenuFind = true
        val itemName: String
        println("\n============ Search Item ============")
        print("What do you want to search ? \n1. Item code \n2. Item name \nSelect option 1 - 2 : ")
        menu = sc.nextInt()
        sc.nextLine()
        when (menu) {
            1 -> {
                print("Input item code : ")
                itemCode = sc.nextLine()
                itemIndex = findItemCode(frameInventory, itemCode)
            }
            2 -> {
                print("Input item name : ")
                itemName = sc.nextLine()
                itemIndex = findItemName(frameInventory, itemName)
            }
            else -> println("\t\tInvalid option! select option correctly")
        }
        if (itemIndex == -1) {
            println("Item not found!")
        } else {
            while (subMenuFind) {
                println("\nItem found! Here's item that you want")
                println("| No | Code | Item Name      | Stock | Broken |")
                System.out.printf(
                    "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", itemIndex + 1,
                    frameInventory[itemIndex][0],
                    frameInventory[itemIndex][1],
                    frameInventory[itemIndex][2], frameInventory[itemIndex][3]
                )
                print("\nOPTION \n1. Edit item \n2. Delete item \n3. Go back\n")
                print("Select option 1 - 3 : ")
                menu = sc.nextInt()
                sc.nextLine()
                when (menu) {
                    1 -> editItem()
                    2 -> deleteItem()
                    3 -> {
                        subMenuFind = false
                        sortAfterDelete()
                    }
                    else -> println("\t\tInvalid option! select option correctly")
                }
            }
        }
    }

    // function to delete item from inventory
    private fun deleteItem() {
        itemIndex = 0
        var subMenuDelete = true
        println("\n============ Delete Item ============")
        print("Search item code : ")
        itemCode = sc.nextLine()
        itemIndex = findItemCode(frameInventory, itemCode)
        if (itemIndex == -1) {
            println("Item not found!")
        } else {
            while (subMenuDelete) {
                println("\nItem found! Here's item that you want")
                println("| No | Code | Item Name      | Stock | Broken |")
                System.out.printf(
                    "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", itemIndex + 1,
                    frameInventory[itemIndex][0],
                    frameInventory[itemIndex][1],
                    frameInventory[itemIndex][2], frameInventory[itemIndex][3]
                )
                print("\nAre you sure to delete this item? ( y / n ) : ")
                yesNo = sc.nextLine()
                if (yesNo.equals("y", ignoreCase = true)) {
                    frameInventory[itemIndex][0] = null
                    frameInventory[itemIndex][1] = null
                    frameInventory[itemIndex][2] = null
                    frameInventory[itemIndex][3] = null
                    subMenuDelete = false
                } else if (yesNo.equals("n", ignoreCase = true)) {
                    subMenuDelete = false
                } else {
                    println("\t\tInvalid option! select option correctly")
                }
            }
        }
    }

    // function to sort after delete item from inventory
    private fun sortAfterDelete() {
        for (i in frameInventory.indices) {
            if (frameInventory[i][0] == null) {
                for (j in i until frameInventory.size - 1) {
                    frameInventory[j][0] = frameInventory[j + 1][0]
                    frameInventory[j][1] = frameInventory[j + 1][1]
                    frameInventory[j][2] = frameInventory[j + 1][2]
                    frameInventory[j][3] = frameInventory[j + 1][3]
                }
            }
        }
    }

    // show borrowing function
    private fun showBorrow() {
        println("\n==================== List Borrowing Data ====================")
        println("| No | Borrowing date | Item code | Item Name      | Amount |")
        for (i in frameBorrowing.indices) {
            if (frameBorrowing[i][0] != null) {
                System.out.printf(
                    "| %-2d | %-14s | %-9s | %-14s | %-6s |\n", i + 1,
                    frameBorrowing[i][0],
                    frameBorrowing[i][1], frameBorrowing[i][2], frameBorrowing[i][3]
                )
            }
        }
    }

    // input new borrowing function
    private fun inputBorrow() {
        var subMenuBorrow = true
        itemIndex = 0
        var itemStock: Int
        showInventory()
        while (subMenuBorrow) {
            print("Search item code to borrow : ")
            itemCode = sc.nextLine()
            itemIndex = findItemCode(frameInventory, itemCode)
            if (itemIndex == -1) {
                println("Item not found!")
            } else {
                println("\nItem found! Here's item that you want")
                println("| No | Code | Item Name      | Stock | Broken |")
                System.out.printf(
                    "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", itemIndex + 1,
                    frameInventory[itemIndex][0],
                    frameInventory[itemIndex][1],
                    frameInventory[itemIndex][2], frameInventory[itemIndex][3]
                )
                print("How many stock to borrow : ")
                itemStock = sc.nextInt()
                sc.nextLine()
                if (frameInventory[itemIndex][2]!!.toInt() >= itemStock) {
                    frameInventory[itemIndex][2] = (frameInventory[itemIndex][2]!!.toInt() - itemStock).toString()
                    var yesNo: String
                    for (i in frameBorrowing.indices) {
                        if (frameBorrowing[i][0] == null) {
                            print("Input date ( dd-mm-yyyy ) : ")
                            frameBorrowing[i][0] = sc.nextLine()
                            frameBorrowing[i][1] = itemCode
                            print("Input name : ")
                            frameBorrowing[i][2] = sc.nextLine()
                            frameBorrowing[i][3] = itemStock.toString()
                            println("Item borrowed!")
                            print("Do you want input again? ( y / n ) : ")
                            yesNo = sc.nextLine()
                            if (yesNo.equals("n", ignoreCase = true)) {
                                break
                            }
                        }
                    }
                    showBorrow()
                    break
                } else {
                    println("Item stock is not enough!")
                }
            }
            print("Do you want to borrow again? ( y / n ) : ")
            yesNo = sc.nextLine()
            if (yesNo.equals("n", ignoreCase = true)) {
                subMenuBorrow = false
            }
        }
    }

    //adding stock function
    private fun addStock() {
        var menuAdd = true
        var subMenuAdd = true
        var stockAdd: Int
        while (menuAdd) {
            print("Input item code to add the stock : ")
            itemCode = sc.nextLine()
            itemIndex = findItemCode(frameInventory, itemCode)
            if (itemIndex == -1) {
                println("item not found!")
            } else {
                while (subMenuAdd) {
                    println("\nItem found! Here's item that you want edit : ")
                    println("| No | Code | Item Name      | Stock | Broken |")
                    System.out.printf(
                        "| %-2d | %-4s | %-14s | %-5s | %-6s |\n", itemIndex + 1,
                        frameInventory[itemIndex][0],
                        frameInventory[itemIndex][1],
                        frameInventory[itemIndex][2], frameInventory[itemIndex][3]
                    )
                    println("\nOPTION? \n1. Add stock \n2. Nothing")
                    print("Select option 1 - 2 : ")
                    menu = sc.nextInt()
                    sc.nextLine()
                    when (menu) {
                        1 -> {
                            print("Input how much stock to add : ")
                            stockAdd = sc.nextInt()
                            frameInventory[itemIndex][2] =
                                (frameInventory[itemIndex][2]!!.toInt() + stockAdd).toString()
                        }
                        2 -> subMenuAdd = false
                        else -> println("\t\tInvalid option! select option correctly")
                    }
                }
                print("Do you want to add again? ( y / n ) : ")
                yesNo = sc.nextLine()
                if (yesNo.equals("n", ignoreCase = true)) {
                    menuAdd = false
                }
            }
        }
    }

    //menu 1 : inventory data function
    private fun inventoryDataMenu() {
        inventoryProcess = true
        while (inventoryProcess) {
            showInventory()
            println("\nOPTION \n1. Input new item \n2. Edit item \n3. Adding stock \n4. Find item \n5. Delete item \n6. Back to main menu ")
            print("Select option 1 - 6 : ")
            menu = sc.nextInt()
            sc.nextLine()
            when (menu) {
                1 -> inputNewItem()
                2 -> editItem()
                3 -> addStock()
                4 -> findItem()
                5 -> {
                    deleteItem()
                    sortAfterDelete()
                }
                6 -> inventoryProcess = false
                else -> println("\t\tInvalid option! select option correctly")
            }
        }
    }

    //menu 2 : borrowing data function
    private fun borrowingDataMenu() {
        borrowingProcess = true
        while (borrowingProcess) {
            println("\nOPTION \n1. Show borrowing data \n2. Input borrowing \n3. Back to main menu")
            print("Select option 1 - 3 : ")
            menu = sc.nextInt()
            sc.nextLine()
            when (menu) {
                1 -> showBorrow()
                2 -> inputBorrow()
                3 -> borrowingProcess = false
                else -> println("\t\tInvalid option! select option correctly")
            }
        }
    }

    //menu 3 : returning function
    private fun returnItem() {
        var subMenuReturn = true
        itemIndex = 0
        var itemStock: Int
        showBorrow()
        while (subMenuReturn) {
            print("Search item code to return : ")
            itemCode = sc.nextLine()
            itemIndex = findBorrowing(frameBorrowing, itemCode)
            if (itemIndex == -1) {
                println("Item not found!")
            } else {
                println("\nItem found! Here's item that you want")
                println("| No | Borrowing date | Item code | Item Name      | Amount |")
                System.out.printf(
                    "| %-2d | %-14s | %-9s | %-14s | %-6s |\n", itemIndex + 1,
                    frameBorrowing[itemIndex][0],
                    frameBorrowing[itemIndex][1],
                    frameBorrowing[itemIndex][2], frameBorrowing[itemIndex][3]
                )
                print("How many stock to return : ")
                itemStock = sc.nextInt()
                sc.nextLine()
                if (frameBorrowing[itemIndex][3]!!.toInt() >= itemStock) {
                    frameBorrowing[itemIndex][3] = (frameBorrowing[itemIndex][3]!!.toInt() - itemStock).toString()
                    var yesNo: String
                    for (i in frameInventory.indices) {
                        if (frameInventory[i][0] == itemCode) {
                            frameInventory[i][2] = (frameInventory[i][2]!!.toInt() + itemStock).toString()
                            println("Item returned!")
                            print("Do you want to return again? ( y / n ) : ")
                            yesNo = sc.nextLine()
                            if (yesNo.equals("n", ignoreCase = true)) {
                                break
                            }
                        }
                    }
                    showInventory()
                    break
                } else {
                    println("Out of borrowed amount!")
                }
                print("Do you want to return again? ( y / n ) : ")
                yesNo = sc.nextLine()
                if (yesNo.equals("n", ignoreCase = true)) {
                    subMenuReturn = false
                }
            }
        }
    }

    //main program
    @JvmStatic
    fun main(args: Array<String>) {
        setFrameItem()
        val line = "==========================================="
        System.out.printf("%s\n\tWANGGSAFF APPLIED SCIENCE UNIVERSITY \n%s", line, line)
        mainProcess = true
        while (mainProcess) {
            println("\nPLEASE LOGIN TO CONTINUE ACCESS THE PROGRAM \n1. Login \n2. Exit Program")
            print("Choose menu 1 - 2 : ")
            menu = sc.nextInt()
            sc.nextLine()
            when (menu) {
                1 -> {
                    menuProcess = true
                    loginPage()
                    if (validationUser) {
                        println("\nWELCOME ADMIN!!")
                        while (menuProcess) {
                            println("\nMENU \n1. Inventory Data \n2. Borrowing Data \n3. Returning \n4. Logout")
                            print("Choose menu 1 - 4 : ")
                            menu = sc.nextInt()
                            sc.nextLine()
                            when (menu) {
                                1 -> inventoryDataMenu()
                                2 -> borrowingDataMenu()
                                3 -> returnItem()
                                4 -> menuProcess = false
                                else -> println("\t\tInvalid menu! Choose menu correctly")
                            }
                        }
                    } else {
                        println("\t\tIncorrect account, Try again!")
                    }
                }
                2 -> mainProcess = false
                else -> println("\t\tInvalid menu! Choose menu correctly")
            }
        }
    }
}