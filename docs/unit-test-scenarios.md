# Unit Test Scenarios

A list of scenarios to test for each respective class.

## model.Consequence

- Valid description
- Empty description, exception
- is short term
- is long term
- both short and long term

## model.Choice

- Constructor defaults
- Change regret value
- Regret value exception
- Lists: cons, pros, regrets; verify size
  - Add
  - Add 2 **references** to **same** object, observe that both are added, remove one, observe list changes in size, observe that can still lookup to find copy, observe that changes to one are not propogated to copies
  - Add 2 **clones** of an object, observe that both are added, remove one, observe list changes in size, observe that can still lookup to find copy, observe that changes to one are not propogated to copies
  - Remove, Consequence exists and size changes
  - Remove, Consequence not exists and size does not change
  - Get
  - Get exception

## model.Entry

## model.Journal

- Constructor: no entries, lastEntryDateShort exception,
- Add + get + lastEntryDateShort should be the same as added
- Add + remove + get exception + no lastEntry date exception
- Get exception
- Multiple operations
  - **Add 2, remove last, ensure lastEntryDateShort changes**
  - **Add 2, remove first, ensure lastEntryDateShort stays the same**
  - Add 2 **references** to **same** Entry object, observe that both are added, remove one, observe list changes in size, observe that changes to one are not propogated to copies
  - Add 2 **clones** of an Entry object, observe that both are added, remove one, observe list changes in size, observe that changes to one are not propogated to copies

## model.Status

Does not need to be tested unless additional behaviour is implemented.
