package com.edi.learn.axon.controller;

import com.edi.learn.axon.commands.CreateAccountCommand;
import com.edi.learn.axon.commands.WithdrawMoneyCommand;
import com.edi.learn.axon.domain.AccountId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison on 2017/3/9.
 */
public class BankAccountController {

    private static final Logger LOGGER = getLogger(BankAccountController.class);

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(method = RequestMethod.POST)
    public void create() {
        LOGGER.info("start");
        AccountId id = new AccountId();
        commandGateway.send(new CreateAccountCommand(id, "MyAccount",1000));
        commandGateway.send(new WithdrawMoneyCommand(id, 500));
        commandGateway.send(new WithdrawMoneyCommand(id, 500));
        /*config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand(id, "MyAccount", 1000)));
        config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand(id, 500)));*/
    }
}
