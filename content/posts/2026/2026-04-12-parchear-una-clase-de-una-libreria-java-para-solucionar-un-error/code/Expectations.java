public static class Expectations implements Expectation {

  ...

  public static class BasicExpectation implements Expectation {

    public final void verifyOutcome(int rowCount, PreparedStatement statement, int batchPosition) {
      rowCount = this.determineRowCount(rowCount, statement);

      if (batchPosition < 0) {
        this.checkNonBatched(rowCount);
      } else {
        this.checkBatched(rowCount, batchPosition);
      }
    }

    protected int determineRowCount(int reportedRowCount, PreparedStatement statement) {
      return reportedRowCount;
    }

    private void checkBatched(int rowCount, int batchPosition) {
        if (rowCount == -2) {
          if (Expectations.log.isDebugEnabled()) {
            Expectations.log.debug("success of batch update unknown: " + batchPosition);
          }
        } else {
          if (rowCount == -3) {
            throw new BatchFailedException("Batch update failed: " + batchPosition);
          }

          if (this.expectedRowCount > rowCount) {
            throw new StaleStateException("Batch update returned unexpected row count from update [" + batchPosition + "]; actual row count: " + rowCount + "; expected: " + this.expectedRowCount);
          }

          if (this.expectedRowCount < rowCount) {
            String msg = "Batch update returned unexpected row count from update [" + batchPosition + "]; actual row count: " + rowCount + "; expected: " + this.expectedRowCount;
            throw new BatchedTooManyRowsAffectedException(msg, this.expectedRowCount, rowCount, batchPosition);
          }
        }
      }
    }
  }
}