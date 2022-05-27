package org.unibl.etf.master.crypto.wallet.model.request;

import javax.validation.constraints.NotNull;

public record RollbackRequest(@NotNull int betId) {}