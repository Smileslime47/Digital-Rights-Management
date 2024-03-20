package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class Right extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161046538038061046583398101604081905261002f91610087565b60018054600164ffffffff0160a01b0319163363ffffffff60a81b191617600160a81b63ffffffff851602179055600061006982826101f8565b5050506102b7565b634e487b7160e01b600052604160045260246000fd5b6000806040838503121561009a57600080fd5b825163ffffffff811681146100ae57600080fd5b602084810151919350906001600160401b03808211156100cd57600080fd5b818601915086601f8301126100e157600080fd5b8151818111156100f3576100f3610071565b604051601f8201601f19908116603f0116810190838211818310171561011b5761011b610071565b81604052828152898684870101111561013357600080fd5b600093505b828410156101555784840186015181850187015292850192610138565b60008684830101528096505050505050509250929050565b600181811c9082168061018157607f821691505b6020821081036101a157634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156101f3576000816000526020600020601f850160051c810160208610156101d05750805b601f850160051c820191505b818110156101ef578281556001016101dc565b5050505b505050565b81516001600160401b0381111561021157610211610071565b6102258161021f845461016d565b846101a7565b602080601f83116001811461025a57600084156102425750858301515b600019600386901b1c1916600185901b1785556101ef565b600085815260208120601f198616915b828110156102895788860151825594840194600190910190840161026a565b50858210156102a75787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b61019f806102c66000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c80631e3b582d14610030575b600080fd5b61003861004e565b60405161004591906100e0565b60405180910390f35b60606000805461005d9061012f565b80601f01602080910402602001604051908101604052809291908181526020018280546100899061012f565b80156100d65780601f106100ab576101008083540402835291602001916100d6565b820191906000526020600020905b8154815290600101906020018083116100b957829003601f168201915b5050505050905090565b60006020808352835180602085015260005b8181101561010e578581018301518582016040015282016100f2565b506000604082860101526040601f19601f8301168501019250505092915050565b600181811c9082168061014357607f821691505b60208210810361016357634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212208f56be3eddc4734c1a2348d65b9bce903a7eda5dcb1ba0c2c2ec047f72689f0864736f6c63430008190033";

    public static final String FUNC_GETRIGHTNAME = "getRightName";

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getRightName() {
        final Function function = new Function(FUNC_GETRIGHTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger iss, String name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(iss), 
                new org.web3j.abi.datatypes.Utf8String(name)));
        return deployRemoteCall(Right.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger iss, String name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(iss), 
                new org.web3j.abi.datatypes.Utf8String(name)));
        return deployRemoteCall(Right.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger iss, String name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(iss), 
                new org.web3j.abi.datatypes.Utf8String(name)));
        return deployRemoteCall(Right.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger iss, String name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(iss), 
                new org.web3j.abi.datatypes.Utf8String(name)));
        return deployRemoteCall(Right.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
